package com.thiagomuller.service.tags;

import com.thiagomuller.service.IdValidator;

public interface TagValidator extends IdValidator{
	public boolean isTagNameEmpty(String tagName);
	public boolean doesIdAlreadyExists(Integer tagId);
	public boolean doesTagNameAlreadyExists(String tagName);
}
