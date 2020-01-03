package md.electron.electronbackend.service;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.persistence.model.User;

public interface UserService
{
    User createUser(CheckoutData checkoutData);
}
