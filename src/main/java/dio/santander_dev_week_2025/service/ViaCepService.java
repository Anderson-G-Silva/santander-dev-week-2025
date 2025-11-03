package dio.santander_dev_week_2025.service;

import dio.santander_dev_week_2025.domain.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
    @GetMapping("/{cep}/json/") // ou @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Address checkCep(@PathVariable("cep") String cep);
}
