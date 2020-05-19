# desafioStarWars
Link da aplicação rodando no heroku:
```
https://desafio-swapi.herokuapp.com/api/planet
```
### Endpoints
#### Para buscar todos os items:
```
http://localhost:8080/api/planet/
```
#### Para buscar pelo id:
```
http://localhost:8080/api/planet/{id}
```
#### Para buscar pelo nome:
```
http://localhost:8080/api/planet/nome/{nome}
```
#### Para salvar um item:
```
http://localhost:8080/api/planet/
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
http://localhost:8080/api/planet/{id}
```
