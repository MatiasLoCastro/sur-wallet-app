# sur-wallet-app
Kotlin app final TP3

¿Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla? 
- Decidí usar la arquitectura MVVM (Model-View-ViewModel) ya que la app era de tamañan mediano a pequeña, se podría mejorar claramente para escalarla con una buena capa de datos para consultas a la db por ejemplo.
  
¿Tuvieron objetos stateful y stateless? ¿Cómo definen la elección de los mismos?
- Use objetos stateful para hacer actualizaciones en tiempo real.

¿Qué mejoras detectan que podrían realizarle a la app? ¿Tendrían side effect si escala el volumen de datos? Comenten al menos 2 cuestiones a refactorizar y tener en cuenta. 
- Le haría mejoras a la arquitectura separando mejor la lógica de negocio de los repositorios, mejoraría el diseño y reutilizaría y ordenaría mejor el código.

¿Qué manejo de errores harían? ¿dónde los contemplan a nivel código? Mencionen la estrategia de mapeo que más se adecúe.
- Para los errores crearía mis propias excepciones y un global exception handler para tenerlas bien ordenadas y sea más fácil de mantener

En el caso de uso de persistencia para el Login, ¿que estrategia sugieren?.
- Usaría un servicio de auth con token como por ejemplo cognito para persistir datos de sesión, con vencimiento. Pero lo haría desde el backend, creo que sería lo ideal.

Si la tendríamos que convertir a Inglés y conservar el Español, qué estrategia utilizaría para extenderla. Y si necesitamos agregar otros idiomas?
- Lo haría mediante carga de archivos xml y así podríamos ir agregando idiomas mientras los necesitemos.

