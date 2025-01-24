package com.groovehub.party.repository;

import com.groovehub.auth.model.User;
import com.groovehub.party.model.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByJoinCode(String joinCode);

    List<Party> findByHost(User host);

    @Query("SELECT DISTINCT p FROM Party p JOIN p.members m WHERE m.user.userId = :userId")
    List<Party> findActivePartiesByUserId(@Param("userId") Long userId);

    boolean existsByJoinCode(String joinCode);

    List<Party> findByPartyNameContainingIgnoreCase(String searchTerm);

    @Query("SELECT p FROM Party p " +
            "WHERE SIZE(p.songs) > 0")
    List<Party> findPartiesWithSongs();

    @Query("SELECT p FROM Party p " +
            "JOIN p.games g " +
            "WHERE g.endedAt IS NULL")
    List<Party> findPartiesWithActiveGames();

    @Query("SELECT COUNT(m) FROM Party p JOIN p.members m WHERE p.partyId = :partyId ")
    long countActiveMembers(@Param("partyId") Long partyId);

    @Query("SELECT p FROM Party p " +
            "WHERE p.createdAt >= :startTime")
    List<Party> findRecentlyCreatedParties(@Param("startTime") LocalDateTime startTime);

    @Query("DELETE FROM Party p " +
            "WHERE p.updatedAt < :cutoffTime " +
            "AND NOT EXISTS (SELECT m FROM p.members m WHERE m.active = true)")
    void deleteInactiveParties(@Param("cutoffTime") LocalDateTime cutoffTime);

    List<Party> findByHostUserId(Long hostId);

    @Query("SELECT p FROM Party p " +
            "WHERE EXISTS (SELECT m FROM p.members m WHERE m.active = true)")
    Page<Party> findActiveParties(Pageable pageable);

    @Query("SELECT p FROM Party p " +
            "WHERE (:partyName IS NULL OR LOWER(p.partyName) LIKE LOWER(CONCAT('%', :partyName, '%'))) " +
            "AND (:hostId IS NULL OR p.host.userId = :hostId) "
    )
    Page<Party> searchParties(
            @Param("partyName") String partyName,
            @Param("hostId") Long hostId,
            @Param("hasActiveGames") Boolean hasActiveGames,
            Pageable pageable);
}
