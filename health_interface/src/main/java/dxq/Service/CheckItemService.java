package dxq.Service;

import dxq.bean.CheckItem;
import dxq.entity.PageResult;
import dxq.entity.QueryPageBean;

import java.util.List;

public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public void deleteItemById(Integer id);
    public void editItem(CheckItem checkItem);
    public CheckItem findItemById(Integer id);
    public List<CheckItem> getAllCheckItem();
}

