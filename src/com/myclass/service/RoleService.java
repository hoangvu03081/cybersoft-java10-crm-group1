package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository;
	
	public RoleService() {
		roleRepository = new RoleRepository();
	}
	
	public List<RoleDto> getAllRoles(){
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		List<Role> entities = roleRepository.getAllRoles();
		
		for (Role entity : entities) {
			RoleDto dto = new RoleDto(
					entity.getRoleId(), 
					entity.getRoleName(), 
					entity.getRoleDescription()
					);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	public boolean addRole(RoleDto dto) {
		Role entity = new Role(
				dto.getRoleName(), 
				dto.getRoleDescription());
		
		return roleRepository.addRole(entity);
	}
	
	public boolean editRole(RoleDto dto) {
		Role entity = roleRepository.getRoleById(dto.getRoleId());
		if (entity != null) {
			entity.setRoleId(dto.getRoleId());
			entity.setRoleName(dto.getRoleName());
			entity.setRoleDescription(dto.getRoleDescription());
			
			return roleRepository.editRole(entity);
		}
		return false;
	}	
	
	public boolean removeRole(int id) {
		
		return roleRepository.removeRole(id);
	}
	
	public RoleDto getRoleById(int id) {
	RoleDto dto = new RoleDto();
		Role entity = roleRepository.getRoleById(id);

		dto.setRoleId(entity.getRoleId());		
		dto.setRoleName(entity.getRoleName());
		dto.setRoleDescription(entity.getRoleDescription());
		
		return dto;
	}
}
