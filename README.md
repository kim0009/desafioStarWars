# desafioStarWars
[Link](https://desafio-swapi.herokuapp.com/api/planet) da aplicação rodando no heroku.
### Endpoints
#### Para buscar todos os items:
```
https://desafio-swapi.herokuapp.com/api/planet/
```
#### Para buscar pelo id:
```
https://desafio-swapi.herokuapp.com/api/planet/{id}
```
#### Para buscar pelo nome:
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
