package zoo_webapp.zoomanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zoo_webapp.zoomanager.Animal.Animal;
import zoo_webapp.zoomanager.Animal.AnimalsDAO;
import zoo_webapp.zoomanager.Client.Client;
import zoo_webapp.zoomanager.Client.ClientsDAO;
import zoo_webapp.zoomanager.Employee.Employee;
import zoo_webapp.zoomanager.Employee.EmployeesDAO;
import zoo_webapp.zoomanager.Enclosure.Enclosure;
import zoo_webapp.zoomanager.Enclosure.EnclosuresDAO;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AppController {

    //@Autowired
    //private ZooDAO zooDAO;
    @Autowired
    private EnclosuresDAO enclosuresDAO;
    @Autowired
    private AnimalsDAO animalsDAO;
    @Autowired
    private ClientsDAO cDAO;
    @Autowired
    private EmployeesDAO employeesDAO;

    private Client currentClient;
    private int currentClientId;
    private int updatedId;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        return "index";
    }

    @RequestMapping(value="/directorView")
    public String viewZooPageDirector(ModelAndView model){
        return "director_view";
    }

    @RequestMapping(value="/guestView")
    public String viewZooPageGuest(@ModelAttribute("client") Client client, Model model){
        //cDAO.save(client);
        List<Animal> listAnimals = animalsDAO.list();
        //clientId = cDAO.get(client.getName(),client.getSurname()).getId();
        model.addAttribute("listAnimals", listAnimals);
        return "guest_view";
    }

    //KLIENCI
    @RequestMapping(value="/save/client", method = POST)
    public String saveClient(@ModelAttribute("client") Client client){
        currentClient = client;
        //System.out.println("/save/cl " +client);
        cDAO.save(client);
        currentClientId = client.getId();
        //clientId = client.getId();
        //model.addAttribute("client", client);
        return "redirect:/guestView";
    }

    @RequestMapping(value="/update/client")
    public ModelAndView updateClient(Model model){
        //cDAO.update(client);
        //clientId = client.getId();
        //System.out.println("update " + currentClient.toString());
        ModelAndView mav = new ModelAndView("editformClient");
        currentClient.setId(cDAO.getRightId(currentClient));
        //System.out.println("po prawidlowym id: "+ currentClient.toString());
        mav.addObject("client", currentClient);
        //mav.addObject("currentClient", client1);
        return mav;
        //return "redirect:/guestView";
    }

    @RequestMapping(value="/saveEdit", method = POST)
    public String saveUpdateClient(@ModelAttribute("client") Client clien)
    {
        //System.out.println("I'm in saveUpdateClient "+clien.toString());
        clien.setId(currentClientId);
        //System.out.println("Po zmianie id w saveUpdateClient: "+clien.toString());
        cDAO.update(clien);
        return "redirect:/guestView";
    }

    //WYBIEGI
    @RequestMapping("/new/enclosure")
    public String showNewFormEnclosure(Model model){
        Enclosure enclosure = new Enclosure();
        model.addAttribute("enclosure", enclosure);
        return "new_form";
    }

    @RequestMapping(value = "/enclosures")
    public String viewEnclosures(Model model){
        List<Enclosure> listEnclosure = enclosuresDAO.list();
        model.addAttribute("list", listEnclosure);
        //System.out.println(listEnclosure);
        return "enclosures";
    }

    @RequestMapping(value="/save/enclosure", method = POST)
    public String saveEnclosure(@ModelAttribute("enclosure") Enclosure enclosure){
        enclosuresDAO.save(enclosure);
        return "redirect:/enclosures";
    }

    @RequestMapping("/edit/enclosure/{id}")
    public ModelAndView showEditFormEnclosure(@PathVariable(name="id") int id){
        updatedId = id;
        ModelAndView mav = new ModelAndView("editform");
        Enclosure enclosure = enclosuresDAO.get(id);
        mav.addObject("enclosure", enclosure);
        return mav;
    }

    @RequestMapping(value="/update/enclosure", method= POST)
    public String updateEnclosure(@ModelAttribute("enclosure") Enclosure enclosure){
        enclosure.setId(updatedId);
        //System.out.println(enclosure.toString());
        enclosuresDAO.update(enclosure);
        return "redirect:/enclosures";
    }

    @RequestMapping("/delete/enclosure/{enclosure.id}")
    public String deleteEnclosure(@PathVariable(name = "enclosure.id") int id){
        enclosuresDAO.delete(id);
        return "redirect:/enclosures";
    }

    //ZWIERZETA
    @RequestMapping(value="/animals")
    public String viewAnimals(Model model){
        List<Animal> listAnimal = animalsDAO.list();
        model.addAttribute("listAnimals", listAnimal);
        return "animals";
    }

    @RequestMapping("/new/animal")
    public String showNewFormAnimal(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "new_form_animal";
    }

    @RequestMapping(value="/save/animal", method = POST)
    public String saveAnimal(@ModelAttribute("animal") Animal animal){
        animalsDAO.save(animal);
        return "redirect:/animals";
    }

    @RequestMapping("/edit/animal/{id}")
    public ModelAndView showEditFormAnimal(@PathVariable(name="id") int id){
        updatedId = id;
        ModelAndView mav = new ModelAndView("editformAnimal");
        Animal animal = animalsDAO.get(id);
        mav.addObject("animal", animal);
        return mav;
    }

    @RequestMapping(value="/update/animal", method= POST)
    public String updateAnimal(@ModelAttribute("animal") Animal animal){
        animal.setId(updatedId);
        //System.out.println(enclosure.toString());
        animalsDAO.update(animal);
        return "redirect:/animals";
    }

    @RequestMapping("/delete/animal/{animal.id}")
    public String deleteAnimal(@PathVariable(name = "animal.id") int id){
        animalsDAO.delete(id);
        return "redirect:/animals";
    }

    //ZWIERZETA
    @RequestMapping(value="/employees")
    public String viewEmployees(Model model){
        List<Employee> listEmployee = employeesDAO.list();
        model.addAttribute("listEmployees", listEmployee);
        return "employees";
    }

    @RequestMapping("/new/employee")
    public String showNewFormEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_form_employee";
    }

    @RequestMapping(value="/save/employee", method = POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeesDAO.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping("/edit/employee/{id}")
    public ModelAndView showEditFormEmployee(@PathVariable(name="id") int id){
        updatedId = id;
        ModelAndView mav = new ModelAndView("editformEmployee");
        Employee employee = employeesDAO.get(id);
        mav.addObject("employee", employee);
        return mav;
    }

    @RequestMapping(value="/update/employee", method= POST)
    public String updateEmployee(@ModelAttribute("employee") Employee employee){
        employee.setId(updatedId);
        //System.out.println(enclosure.toString());
        employeesDAO.update(employee);
        return "redirect:/employees";
    }

    @RequestMapping("/delete/employee/{employee.id}")
    public String deleteEmployee(@PathVariable(name = "employee.id") int id){
        employeesDAO.delete(id);
        return "redirect:/employees";
    }

    
}
