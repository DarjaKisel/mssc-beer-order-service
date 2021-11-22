package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.services.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BreweryService {
    Optional<BeerDto> getBeerById(UUID id);
    Optional<BeerDto> getBeerByUpc(Long upc);
}
