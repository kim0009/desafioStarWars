# desafioStarWars
A aplicação se resume em brincar com os planetas dos filmes de Star Wars, podendo adicionar, listar e remover um planeta. Além disso, também conta com informações da swapi(api com informações dos filmes de star wars) para saber a quantidade de filmes em que cada planeta aparece.
Aplicação está rodando no heroku.
### Endpoints
#### Para buscar todos os items: [exemplo](https://desafio-swapi.herokuapp.com/api/planet)
```
https://desafio-swapi.herokuapp.com/api/planet/
```
#### Para buscar pelo id: [exemplo](https://desafio-swapi.herokuapp.com/api/planet/946358d2-bee1-439c-98b1-b96fe4bc3aa6) 
```
https://desafio-swapi.herokuapp.com/api/planet/{id}
```
#### Para buscar pelo nome: [exemplo](https://desafio-swapi.herokuapp.com/api/planet/nome/Alderaan) 
```
https://desafio-swapi.herokuapp.com/planet/nome/{nome}
```
#### Para salvar um item:
```
https://desafio-swapi.herokuapp.com/api/planet/
```
Exemplo do formato a ser enviado:
```
{
	"clima": "temperate, arid",
	"terreno": "rock, desert, mountain, barren",
	"nome": "Geonosis"
}
```
#### Para excluir um item:
```
https://desafio-swapi.herokuapp.com/api/planet/{id}
```
