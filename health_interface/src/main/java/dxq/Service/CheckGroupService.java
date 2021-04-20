package dxq.Service;

import dxq.bean.CheckGroup;
import dxq.entity.PageResult;
import dxq.entity.QueryPageBean;

import java.util.List;

public interface CheckGroupService {
    public void addCheckGroup(Integer[] checkItemIds, CheckGroup checkGroup);
    public PageResult findPage(QueryPageBean queryPageBean);
    public void deleteCheckGroup(Integer id);
    public CheckGroup getCheckGroup(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void editGroup(CheckGroup checkGroup,Integer[] checkItemIds);
    public List<CheckGroup> getAll();
}
