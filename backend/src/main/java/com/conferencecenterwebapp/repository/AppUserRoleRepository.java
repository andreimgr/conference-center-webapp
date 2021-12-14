package com.conferencecenterwebapp.repository;

import com.conferencecenterwebapp.model.AppRole;
import com.conferencecenterwebapp.model.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {
    AppUserRole findAppUserRoleByName(AppRole name);
}
