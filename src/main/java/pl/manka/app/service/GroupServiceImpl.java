package pl.manka.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manka.app.domain.Group;
import pl.manka.app.repositories.GroupRepository;
@Service
public class GroupServiceImpl implements GroupService {


    private GroupRepository groupRepository;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Iterable<Group> getAllGroups() {
        return this.groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Integer id) {
        return this.groupRepository.findOne(id);
    }

    @Override
    public void saveGroup(Group group) {
       this.groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Group group) {
        this.groupRepository.delete(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        this.groupRepository.delete(id);
    }
}
