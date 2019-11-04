package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService
{
    private static final String ORDER = "order";

    @Override
    public HttpSession getCurrentSession()
    {
        return getHttpSession();
    }

    private HttpSession getHttpSession()
    {
        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpSession session = attr.getRequest().getSession(true); // true == allow create
        return session;
    }

    @Override
    public boolean hasSessionOrder()
    {
        final Object attribute = getHttpSession().getAttribute(ORDER);
        return attribute != null;
    }

    @Override
    public void setSessionOrder(final Order order)
    {
        getHttpSession().setAttribute(ORDER, order);
    }

    @Override
    public Order getSessionOrder()
    {
        return (Order) getHttpSession().getAttribute(ORDER);
    }
}
