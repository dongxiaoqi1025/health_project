package dxq.Service;

import dxq.bean.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrdSettingService {
    public void addOrdSetting(List<OrderSetting> orderSetting);
    public List<Map> getOrderSettingByMonth(String date);
}
