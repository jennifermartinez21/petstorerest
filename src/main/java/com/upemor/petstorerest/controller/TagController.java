package com.upemor.petstorerest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upemor.petstorerest.exception.TagErrorException;
import com.upemor.petstorerest.model.TagDTO;
import com.upemor.petstorerest.service.TagService;

@RestController
@RequestMapping("/api/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;

	@GetMapping("/")
	public ResponseEntity<List<TagDTO>> listAllTags() {
		List<TagDTO> tags = tagService.listAllTags();
		if (tags.isEmpty()) {
			return new ResponseEntity<List<TagDTO>>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<List<TagDTO>>(tags, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TagDTO> findById(@PathVariable("id") Integer id) {
		TagDTO tag = tagService.findById(id);
		if (tag == null) {
			return new ResponseEntity<TagDTO>(
			new TagErrorException("Tag with id "
			+ id + " not found"), HttpStatus.NOT_FOUND);
			}
		return new ResponseEntity<TagDTO>(tag, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TagDTO> createTag(@RequestBody final TagDTO tag) {
		if(!tagService.createTag(tag)) {
			return new ResponseEntity<TagDTO>(new TagErrorException(
					"Unable to create new tag. A tag with name already exist."),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<TagDTO>(tag, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TagDTO> updateTag(@PathVariable("id") final int id, @RequestBody TagDTO tag) {
		TagDTO currenttag = tagService.findById(id);
		if (currenttag == null) {
		return new ResponseEntity<TagDTO>(
		new TagErrorException("Unable to upate. Tag with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		TagDTO updatedTag = tagService.updateTag(id, tag);
		return new ResponseEntity<TagDTO>(updatedTag, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TagDTO> deleteTag(@PathVariable("id") final int id) {
		TagDTO currenttag = tagService.findById(id);
		if (currenttag == null) {
		return new ResponseEntity<TagDTO>(
		new TagErrorException("Unable to delete Tag with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		tagService.deleteTag(id);
		return new ResponseEntity<TagDTO>(HttpStatus.NO_CONTENT);
	}

}
