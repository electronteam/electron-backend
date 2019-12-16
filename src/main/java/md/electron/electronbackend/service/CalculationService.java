package md.electron.electronbackend.service;

import md.electron.electronbackend.persistence.model.Order;

public interface CalculationService
{
    void calculate(Order order);
}
