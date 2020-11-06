package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.TagDTO;
import com.upemor.petstorerest.repository.TagRepository;


@Service
public class TagServiceImp implements TagService{

	@Autowired
	private TagRepository tagRepository;

	
	public List<TagDTO> listAllTags() {
		// TODO Auto-generated method stub
		return tagRepository.findAll();
	}

	
	public TagDTO findById(int id) {
		// TODO Auto-generated method stub
		TagDTO tag = tagRepository.findById(id);
		return tag;
	}

	
	public boolean createTag(TagDTO tag) {
		if (tagRepository.findByname(tag.getName()) != null){
			return false;
			}
		tagRepository.saveAndFlush(tag);
		return true;
	}

	
	public TagDTO updateTag(int id, TagDTO tag) {
		TagDTO currentTag = tagRepository.findById(id);
		currentTag.setName(tag.getName());
		tagRepository.saveAndFlush(currentTag);
		return currentTag;
	}

	
	public void deleteTag(int id) {
		// TODO Auto-generated method stub
		tagRepository.deleteById(id);
	}

}
