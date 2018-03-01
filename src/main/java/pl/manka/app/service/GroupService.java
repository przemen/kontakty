package pl.manka.app.service;

import pl.manka.app.domain.Group;



public interface GroupService {
    Iterable<Group> getAllGroups();
    Group getGroupById(Integer id);
    void saveGroup(Group group);
    void deleteGroup(Group group);
    void deleteGroup(Integer id);
}
