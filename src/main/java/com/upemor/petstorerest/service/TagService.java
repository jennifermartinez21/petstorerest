package com.upemor.petstorerest.service;

import java.util.List;

import com.upemor.petstorerest.model.TagDTO;

public interface TagService {
	
	List<TagDTO> listAllTags();
	
	TagDTO findById(int id);
	
	boolean createTag(TagDTO tag);
	
	TagDTO updateTag(int id, TagDTO tag);
	
	void deleteTag(int id);

}
