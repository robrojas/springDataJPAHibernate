package udemy.course.springDataJPAHibernate;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import udemy.course.springDataJPAHibernate.model.Customer;
import udemy.course.springDataJPAHibernate.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaHibernateApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testFindByEmailAndName() {
		List<Customer> customers = customerRepository.findByEmailAndName("customer@email.com", "customer");
		customers.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindByEmailLike() {
		List<Customer> customers = customerRepository.findByEmailLike("%customer%");
		customers.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFindByIdIn() {
		List<Customer> customers = customerRepository.findByIdIn(Arrays.asList(1L,2L,3L), PageRequest.of(0, 10));
		customers.forEach(p -> System.out.println(p));
	}

	@Test
	public void testFIndAllPaging() {
		PageRequest pegeable = PageRequest.of(0, 2);
		Page<Customer> customers = customerRepository.findAll(pegeable);

		customers.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void testFIndAllSort() {
		PageRequest pegeable = PageRequest.of(0, 2);
		Page<Customer> customers = customerRepository.findAll(pegeable);

		customers.forEach(p -> System.out.println(p));
	}
	
	@Test
	public void testFIndAllSortASC() {
		Sort sort = Sort.by("name");
		customerRepository.findAll(sort).forEach(p -> System.out.println(p));
	}
	
	@Test
	public void testFIndAllSortDESC() {
		Sort sort = Sort.by(Direction.DESC, "name");
		customerRepository.findAll(sort).forEach(p -> System.out.println(p));
	}
	
	@Test
	public void testFIndAllSortByMultipleProperties() {
		Sort sort = Sort.by("name", "email");
		customerRepository.findAll(sort).forEach(p -> System.out.println(p));
	}
	
	@Test
	public void testFIndAllSortByOrder() {
		Sort sort = Sort.by(Sort.Order.by("name"), Sort.Order.desc("email"));
		customerRepository.findAll(sort).forEach(p -> System.out.println(p));
	}
	
	@Test
	public void testFIndAllPagingAndSorting() {
		PageRequest pageable = PageRequest.of(0, 2, Direction.DESC, "name"); 
		customerRepository.findAll(pageable).forEach(p -> System.out.println(p));
	}
	
	@Test
	public void testFIndAllBycustomFinder() {
        PageRequest pageable = PageRequest.of(0, 2,Sort.Direction.DESC,"name");
		customerRepository.findByIdIn(Arrays.asList(1L,2L,3L), pageable).forEach(c -> System.err.println(c));
	}

}
