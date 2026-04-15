### 

# Agencia de Viajes - Proyecto DAW

## Tecnologías

- HTML, CSS, JavaScript  
- Java (Spring Boot)  
- PosgreeSQL

## Cómo ejecutar

1. 
   

## Notas git

### Para clonar el repositorio:

`git clone git@github.com:amandodaw/proyecto-intermodular-1dawsemi2026.git`

### Para hacer un commit

`git add .`  Añadir seguimiento a los ficheros en git

`git commit -m "Comentario del commit"` Para hacer un commit

`git push` Para subirlo. Aquí hay que tener el SSH o te pedira usuario y contraseña de github.

Para trabajar, cada uno tendremos que crearnos nuestras propias ramas

`git branch` para ver las ramas y `git branch -a` para ver las ramas que existen en el repositorio online pero aun no existen en nuestro ordenador (puede que no haga falta si no necesitamos nada de la rama de otro que no sea develop)
`git branch nombredelarama` para crear una rama, o `git checkout -b nombrerama` para crear y moverse a la rama de un solo comando. 

`git checkout nombrerama` para cambiar de rama

Cuando se crea una rama y quieres subir los cambios, pero la rama aun no ha sido subida al directorio de github, puedes/debes usar este comando

`git push --set-upstream origin nombrerama` esto enlazará la branch de vuestro repositorio local y la creara en github.


