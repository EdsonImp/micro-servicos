package projeto.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.hrpayroll.entities.Payment;
import projeto.hrpayroll.entities.Worker;
import projeto.hrpayroll.feignclients.WorkerFeignClient;

@Service //para dizer que é um serviço, e um componente também
public class PaymentService {
	
	//pegando dados criados no application.properties
	//@Value("${hr-worker.host}")
	//private String workHost;//salvando essa propriedade em uma string
	
	//aki implemente o servico resttemplate criado
	//@Autowired
	//private RestTemplate restTemplate;
	@Autowired//agora usaremos o feign para as reuisicoes entre projetos
	private WorkerFeignClient workerFeignClient;
	
	//aki instancia um pagamento 
	public Payment getPayment(long workerId, int days) {
		//Cria um papa de variaveis para receber os valores que vem por parametros acima
		//Map é só interface, precisa ser instanciado por uma class, nesse caso HashMap
		//Map<String, String> uriVariables = new HashMap<>();
		//agora pega os valores
		//Esse "id" pega o valor que vem do param que é workId
		//uriVariables.put("id", ""+workerId); //as duas aspas é para fazer cast de long para string
		
		//Instancia uma worker passando a requisicao resttemplate com os parametros
		//endereço(url que vem da propriedade + a req get)
		//o tipo do objeto que é a classe
		//e o parametro que é o id, convertido acima por uma map em string
		//Worker worker = restTemplate.getForObject(workHost + "/workers/{id}",Worker.class, uriVariables);
		//retorna o resultado como novo payment armazenado em work com os dados
		
		//agora pega os dados do corpo da interface instanciada do workerFegn
	
		Worker worker = workerFeignClient.findById(workerId).getBody();
		
		return new Payment(worker.getName(),worker.getDailyIncome(), days);
	
	}

}
