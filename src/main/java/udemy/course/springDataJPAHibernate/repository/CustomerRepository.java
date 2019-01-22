package udemy.course.springDataJPAHibernate.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import udemy.course.springDataJPAHibernate.model.Customer;

public interface CustomerRepository  extends PagingAndSortingRepository<Customer, Long> {
	
	List<Customer> findByEmailAndName(String email, String name);
	
	List<Customer> findByEmailLike(String email);
	
	List<Customer> findByIdIn(List<Long> list, Pageable pageable);
}
