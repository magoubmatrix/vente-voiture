package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

	//public Optional<List<Voiture>> findByMarqueOrModeleOrImmatriculationOrPrix(String marque, String modele, int immatriculation, double prix);
	

	@Query("select v from Voiture v where v.marque = :x and v.modele = :y and v.immatriculation = :z and v.prix <= :w ")
	public Page<Voiture> findByAll(@Param("x") String marque, @Param("y")String modele, @Param("z")int immatriculation, @Param("w")double prix,Pageable pageable);
	
	public Page<Voiture>  findByMarque(String marque ,Pageable pageable);
	
	public Page<Voiture>  findByImmatriculation(String immatriculation ,Pageable pageable);
	
	@Query("select v from Voiture v where v.prix <= ?1")
	public Page<Voiture>  findByPrixMax(double prixmax ,Pageable pageable);
	
	public Page<Voiture> findByMarqueAndModele(String marque,String modele ,Pageable pageable);
	
    public Page<Voiture>  findByMarqueAndImmatriculation(String marque,int immatriculation ,Pageable pageable);
	
    @Query("select v from Voiture v where v.marque = ?1 and v.prix <= ?2")
    public Page<Voiture>  findByMarqueAndPrix(String marque, double prixmax ,Pageable pageable);
	
    public Page<Voiture> findByMarqueAndModeleAndImmatriculation(String marque,String modele,int immatriculation ,Pageable pageable);
	
	@Query("select v from Voiture v where v.marque = ?1 and v.modele = ?2 and v.prix = ?3")
	public Page<Voiture>  findByMarqueAndModeleAndPrixMax(String marque,String modele, double prixMax ,Pageable pageable);
	
	
	@Query("select v from Voiture v where v.marque = ?1 and v.immatriculation = ?2 and v.prix <= 3")
	public Page<Voiture>  findByMarqueAndImmatriculationAndPrixMax(String marque,String modele,int immatriculation ,Pageable pageable); 
	

	@Query("select v from Voiture v where v.immatriculation = :x and v.prix <= :y")
	public Page<Voiture>  findByImmatriculationAndPrixMax(@Param("x") int immatriculation, @Param("y") double prix ,Pageable pageable );
	
	
	
	/*
	 * @Query("select count(v) from Voiture v where v.marque = ?1") public int
	 * nombreDesVoitureParMarque( String marque);
	 */
	
	@Query("select count(v) from Voiture v")
	public  long countAll();
	
	public long countByMarque(String marque);
	
	public long countByMarqueAndModele(String marque, String modele);
	
	public long countByMarqueAndImmatriculation(String marque,int immatriculation);
	
	@Query("select count(v) from Voiture v where v.marque = ?1 and v.prix <= ?2")
	public long countByMarqueAndPrixmax(String marque,double prixmax);
	
	public long countByMarqueAndModeleAndImmatriculation(String marque , String modele , int immatriculation);
	
	@Query("select count(v) from Voiture v where v.marque = ?1 and v.immatriculation = ?2 and v.prix <= ?3")
	public long countBymarqueAndImmatriculationAndPrixmax(String marque, int immatriculation , double prixmax);
	
	@Query("select count(v) from Voiture v where v.marque = ?1 and v.modele = ?2 and v.immatriculation = ?3 and v.prix <= ?4 ")
	public long countByAll(String marque , String modele, int immatriculation , double prixmax );
	
	@Query("select count(v) from Voiture v where v.immatriculation = ?1 and v.prix < ?2")
	public long countByimmatriculationAndPrix(int immatriculation , double prix);
	
	public long countByImmatriculation(int immatriculation);
	
	@Query("select count(v) from Voiture v where v.prix < ?1")
	public long countByPrixmax(double prixmax);
	
	// autre type des codes
	
	@Query("select distinct v.marque from Voiture v") 
	public List<String> findAllMarque();
		
	
    @Query("select distinct v.modele from Voiture v where v.marque = ?1")
    public List<String> findModeleByMarque(String marque);
    
	
	 @Query("select distinct v.immatriculation from Voiture v ")
	 public  List<Integer> findAllImmatricul();
	
    
	
}
	

