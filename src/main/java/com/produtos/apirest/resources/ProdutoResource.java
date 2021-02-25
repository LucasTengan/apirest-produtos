package com.produtos.apirest.resources;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Produtos")
@CrossOrigin(origins="*")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/produto/{id}")
    @ApiOperation(value = "Retorna um único produto")
    public Produto listaProduto(@PathVariable("id") Long id) {
        Optional<Produto> produtoProcurado = produtoRepository.findById(id);
        if(produtoProcurado.isPresent()) return produtoProcurado.get();

        return null;
    }

    @PostMapping("/produto")
    @ApiOperation(value = "Salva um produto no banco de dados")
    public Produto salvaProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/produto/{id}")
    @ApiOperation(value = "Deleta um produto do banco de dados")
    public void deletaProduto(@PathVariable("id") Long id) {
        Optional<Produto> produtoProcurado = produtoRepository.findById(id);
        if(produtoProcurado.isPresent()) produtoRepository.deleteById(id);
    }

    @PutMapping("/produto/{id}")
    public Produto atualizaProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
        Optional<Produto> produtoProcurado = produtoRepository.findById(id);
        if(produtoProcurado.isPresent()) return produtoRepository.save(produto);

        return null;
    }
}
