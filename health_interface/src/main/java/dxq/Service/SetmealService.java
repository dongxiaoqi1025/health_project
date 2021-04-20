package dxq.Service;

import dxq.bean.Setmeal;
import dxq.entity.PageResult;
import dxq.entity.QueryPageBean;

import java.util.List;

public interface SetmealService {


    public void  addSetmeal(Setmeal setmeal ,Integer[] checkGroupIds,String lastFileName);
    public PageResult findPage(QueryPageBean queryPageBean);
    public Setmeal getSetmeal(Integer id);
    public Integer[] getSelectId(Integer id);
    public void editMeal(Setmeal setmeal,Integer[] ids,String lastFileName);
    public List<Setmeal> getAllSetmeal();
}
