package md.electron.electronbackend.service;

import md.electron.electronbackend.persistence.model.Order;

import javax.servlet.http.HttpSession;

public interface SessionService
{
    HttpSession getCurrentSession();

    boolean hasSessionOrder();

    void setSessionOrder(Order order);

    void removeSessionOrder();

    Order getSessionOrder();
}
