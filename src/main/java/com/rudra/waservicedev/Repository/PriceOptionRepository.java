package com.rudra.waservicedev.Repository;

import com.rudra.waservicedev.Models.PriceOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceOptionRepository extends JpaRepository<PriceOption, Long>  {

    PriceOption save(PriceOption priceOptions);
}
