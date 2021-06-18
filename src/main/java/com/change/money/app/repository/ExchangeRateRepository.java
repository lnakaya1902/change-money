package com.change.money.app.repository;

import com.change.money.app.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>  {

    @Query("SELECT e FROM ExchangeRate e WHERE e.originCurrency=:originCurrency AND e.destinationCurrency=:destinationCurrency")
    List<ExchangeRate> findByOriginCurrencyAndDestinationCurrency(String originCurrency, String destinationCurrency);
}
