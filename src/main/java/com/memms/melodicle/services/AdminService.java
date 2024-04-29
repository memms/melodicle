package com.memms.melodicle.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.exceptions.UserNotFoundException;

public interface AdminService {
    UserDTO applyPatchToUser(Long uid, JsonPatch jsonPatch) throws JsonProcessingException, JsonPatchException;

    UserDTO getUserById(Long uid);
}
