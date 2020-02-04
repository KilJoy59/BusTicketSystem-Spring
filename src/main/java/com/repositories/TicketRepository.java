package com.repositories;

import com.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findOneByPlace(Integer place);

    List<Ticket> findAllByVoyage_id(Integer voyageId);

}
