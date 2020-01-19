package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.OrderListViewData;
import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.User;
import org.springframework.stereotype.Component;

@Component
public class OrderListViewConverter
{
    public OrderListViewData convert(final Order order)
    {
        final OrderListViewData orderListViewData = new OrderListViewData();
        orderListViewData.setId(order.getId());
        orderListViewData.setTotalPrice(order.getTotalPrice());

        final User user = order.getUser();
        orderListViewData.setUserName(user.getName());
        orderListViewData.setUserLastName(user.getLastName());

        return orderListViewData;
    }
}
