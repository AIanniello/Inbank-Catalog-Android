# Inbank Material Sandbox

Grafica temi Inbank Android

### Gestione branch

Poche regole:

- `master` è la versione che finisce nelle build per Allitude di Inbank e ha versioni finite, tipo `1.1.6`.
- `develop` è la versione di sviluppo, ha versione successiva a `master` e con snapshot `1.1.7-SNAPSHOT`.
- Se dovete fare test in Inbank fatevi nella branch, esempio `feature/232342` del catalog una versione a partire da develop `1.1.7-feature232342`.
- Una volta finite la feature, mergiate la branch in `develop` ma mantenete `1.1.7-SNAPSHOT`.
- Inbank `develop` usa tipicamente la build del catalog `1.1.7-SNAPSHOT` ma potrebbe anche ritrovarsi con una `master` se è da tempo che non lo si tocca.
- Inbank `master` usa sicuramente una build `master` del catalog, ad esempio `1.1.6`.
- Le build `master` del catalog si pubblicano una volta sola: quando vengono appunto mergiate su `master`.
- La pubblicazione della libreria su Maven per le branch `develop` e `master` viene fatta in automatico ad ogni push sulla rispettiva branch
