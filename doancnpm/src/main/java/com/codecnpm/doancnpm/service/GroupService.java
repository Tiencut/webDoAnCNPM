package com.codecnpm.doancnpm.service;

import com.codecnpm.doancnpm.dto.GroupDto;

import java.util.List;

public interface GroupService {
    List<GroupDto> getAllGroups();
    GroupDto getGroupById(Long id);
    GroupDto createGroup(GroupDto groupDto);
    GroupDto updateGroup(Long id, GroupDto groupDto);
    void deleteGroup(Long id);
}