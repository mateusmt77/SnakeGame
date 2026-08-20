 Estrutura atual do projeto

  - Main.java — vazio (só tem o main sem corpo).
  - Model/Direction.java — enum completo (UP/DOWN/LEFT/RIGHT com dx, dy, isOpposite).
  - Model/Point.java — record simples (x, y).
  - Model/Snake.java — corpo da cobra, direção atual/próxima, movimento, reset, colisões.
  - Model/Food.java — vazia.
  - Model/GameState.java — vazia. 

  ---
  Main.java (ponto de entrada)

  1. Inicializar o loop do jogo (game loop com ScheduledExecutorService ou Thread com timer fixo).
  2. Criar a instância de GameState e iniciar o ciclo de jogo.
  3. Configurar o Scanner/BufferedReader para entrada do jogador via teclado (ou detectar plataforma para
  auto-inicializar).
  4. Encerrar o jogo corretamente ao sair (liberar recursos, fechar streams).
  5. Ponto de orquestração entre Model e (futura) View/Controller — hoje, não existe Controller nem View.

  ---
  Model/Direction.java

  Está praticamente pronto, mas faltam pequenos refinamentos:
  1. Método opposite() que retorna a direção oposta sem precisar de outra instância (UP.opposite() == DOWN).
  2. Método estático utilitário para mapear teclas do teclado (W/A/S/D ou setas) para Direction.
  3. Validação para entrada inválida (Direction.fromKey(char) retornando Optional<Direction> ou lançando exceção
  tipada).
  4. Método toString()/name() padronizado para logs.

  ---
  Model/Point.java

  1. Método translate(Direction d) retornando um novo Point deslocado — hoje a Snake faz a aritmética manualmente.
  2. Método add(Point other) para soma vetorial (útil se quiser teletransporte, wrapping etc.).
  3. Método distanceTo(Point other) (Manhattan ou Euclidiana) — útil para spawn de comida longe da cobra.
  4. equals/hashCode estão implícitos pelo record, mas considerar sobrescrever se houver comparação por proximidade.

  ---
  Model/Snake.java (a mais evoluída, mas ainda falta bastante)

  1. Crescimento configurável: hoje só decrementa pedingGrowth. Falta um método público grow(int amount) que some ao
  pendingGrowth (chamado pela lógica de "comeu comida").
  2. Persistir direção entre resets: o reset zera tudo; falta opção de manter pontuação/velocidade ao reiniciar.
  3. Velocidade da cobra: não existe controle de velocidade. Falta campo speed (ms por tick) com getter/setter, e
  métodos increaseSpeed() / resetSpeed().
  4. Sistema de pontuação dentro da Snake: ainda que possa ficar no GameState, a Snake poderia expor getScore() baseado
  em comprimento.
  5. Verificação de colisão com outra cobra (multiplayer) — não existe, mas é uma feature clássica futura.
  6. Wrap-around / teletransporte nas bordas: hoje collisionWithWall mata; falta modo alternativo com Point.translate
  modular.
  7. Power-ups especiais (atravessar parede, invencibilidade temporária, etc.) — exigem atributos extras.
  8. Direção intermediária (queue de inputs): hoje só guarda nextDirection. Falta uma fila para registrar múltiplas 
  viradas dentro de um único tick.
  9. Método público getHead() — hoje getHeadSnake é privado.
  10. Renderização do corpo — a Snake não sabe desenhar; ou expõe getBody() (já existe) e a View lê, ou falta uma camada
  View.
  11. Validação de entrada no setNextDirection: já bloqueia opostas; falta ignorar entradas null ou inválidas
  explicitamente.
  12. Suporte a múltiplos jogadores (cobras distintas na mesma partida).
  13. Invocação de eventos/Listeners: a Snake hoje não notifica ninguém quando morre, come, ou cresce. Falta um
  addListener(SnakeListener) (Observer pattern).
  14. Persistência de high score / estado salvo entre partidas.
  15. Movimento diagonal: hoje o enum é só 4 direções; faltaria expandir ou criar um modo especial.
  16. Método willCollide(Direction d, width, height) para a View/IA simular o próximo passo (preview).

  ---
  Model/Food.java (classe vazia — praticamente tudo)

  1. Atributo Point position (com x, y).
  2. Atributo int points (valor nutricional — pode variar por tipo de comida).
  3. Atributo boolean active (se está no tabuleiro).
  4. Atributo FoodType type (normal, dourada, venenosa, etc.) — viraria enum interno.
  5. Construtor (Point initialPosition) e (int x, int y).
  6. Método respawn(int width, int height, Snake snake) para gerar nova posição aleatória sem cair em cima da cobra.
  7. Método respawn(int width, int height, Predicate<Point> isOccupied) mais genérico.
  8. Método getPosition().
  9. Método getPoints().
  10. Método consume() que marca a comida como inativa.
  11. Método isActive().
  12. Método setType(FoodType) para mudar comportamento por tipo.
  13. Suporte a comida com tempo de vida (spawnTime, despawnAfter).
  14. Suporte a comida especial que dá power-up temporário (crescer 2x, dobrar pontos, etc.).
  15. Método equals/hashCode baseado em posição para evitar duas comidas no mesmo lugar.
  16. Renderização — campo char symbol ou String sprite (se a View ler).
  17. Constantes de pontuação (NORMAL_POINTS, GOLDEN_POINTS).

  ---
  Model/GameState.java (classe vazia — praticamente tudo)

  1. Atributo Snake snake.
  2. Atributo Food food.
  3. Atributo int score (pontuação atual).
  4. Atributo int highScore (recorde).
  5. Atributo int level (nível atual).
  6. Atributo long tickInterval ou int speed (ms por tick, ajustável por nível).
  7. Atributo boolean running (se o jogo está ativo).
  8. Atributo boolean paused.
  9. Atributo boolean gameOver.
  10. Atributo GameMode mode (clássico, sem parede, velocidade crescente, com power-ups etc.) — enum.
  11. Construtor (int width, int height) inicializando Snake + Food.
  12. Método start() / pause() / resume() / stop() / restart().
  13. Método tick() que move a cobra, checa colisão, faz spawn de comida se foi comida.
  14. Método updateDirection(Direction d) repassando para a Snake.
  15. Método isGameOver().
  16. Método getScore() / addScore(int).
  17. Método getHighScore() + persistência (ler/gravar em arquivo).
  18. Método nextLevel() que aumenta velocidade ou muda regras.
  19. Sistema de níveis progressivos (cada N pontos sobe o nível e a velocidade).
  20. Sistema de vidas (atributo lives, decrementa ao morrer, fim só em 0).
  21. Sistema de power-ups ativos (lista de buffs com tempo restante: invencibilidade, lento, dobrar pontos).
  22. Listener/Observable para a View receber atualizações.
  23. Método getSnake(), getFood() para a View.
  24. Gerenciamento de multi-cobra (lista de Snakes, índice do jogador atual).
  25. Estado de menu principal (START, PAUSED, GAME_OVER, PLAYING) — enum GamePhase.
  26. Método saveState() / loadState() para persistência completa.
  27. Ranking/Leaderboard local (top 5).
  28. Configurações do jogo (tamanho do tabuleiro, velocidade inicial) — objeto GameConfig injetado.
  29. Sistema de pontuação por tempo (bônus ao terminar a fase rápido).
  30. Sistema de conquistas/achievements (comida X vezes, sobreviver Y segundos, etc.).
  31. Sistema de eventos aleatórios (após N segundos, surge obstáculo ou comida dourada).

  ---
  Classes que ainda não existem no projeto

  Para fechar a aplicação, vão precisar ser criadas (mesmo que fora do pacote Model):
  - View (ex.: View/GameView.java, View/MenuView.java) — renderização (console com System.out, ou GUI com Swing/JavaFX).
  - Controller/InputHandler — captura de teclas e repassa para GameState.
  - Listener/Observer — para desacoplar Model da View.
  - Config — leitura de tamanho de tela, teclas customizadas, etc.
  - Persistence — salvar high score e preferências.
  - Tests — JUnit para Snake, Direction, Food, GameState.