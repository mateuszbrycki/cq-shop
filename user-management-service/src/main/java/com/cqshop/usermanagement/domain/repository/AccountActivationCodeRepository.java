package com.cqshop.usermanagement.domain.repository;

import com.cqshop.usermanagement.domain.AccountActivationCode;
import com.cqshop.usermanagement.domain.id.AccountActivationCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@Repository
public interface AccountActivationCodeRepository extends JpaRepository<AccountActivationCode, AccountActivationCodeId> {

    Optional<AccountActivationCode> findByUserId(Long id);

}
