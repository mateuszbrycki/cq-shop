package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.query.QueryHandler;
import com.cqshop.cqrs.common.handler.query.QueryHandlerAnnotation;
import com.cqshop.usermanagement.application.query.ListAllNotArchivedUsers;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@Slf4j
@RequiredArgsConstructor
@Component
@QueryHandlerAnnotation
public class ListAllNotArchivedUsersHandler implements QueryHandler<ListAllNotArchivedUsers, List<User>> {

    private final UserRepository userRepository;

    @Override
    public List<User> handle(ListAllNotArchivedUsers listAllNotArchivedUsers) {
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getStatus().equals(com.cqshop.usermanagement.domain.User.Status.ARCHIVED))
                .map(User::of)
                .collect(Collectors.toList());
    }
}
