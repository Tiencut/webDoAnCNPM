package com.codecnpm.doancnpm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codecnpm.doancnpm.dto.GroupDto;
import com.codecnpm.doancnpm.entity.Group;
import com.codecnpm.doancnpm.mapper.GroupMapper;
import com.codecnpm.doancnpm.repository.GroupRepository;
import com.codecnpm.doancnpm.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return groupMapper.toDto(group);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = groupMapper.toEntity(groupDto);
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public GroupDto updateGroup(Long id, GroupDto groupDto) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        existingGroup.setName(groupDto.getName());
        // Update lecturer, courseSection, project, reports if needed
        return groupMapper.toDto(groupRepository.save(existingGroup));
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}