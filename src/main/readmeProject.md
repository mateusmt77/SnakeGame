  1. Estrutura básica e estado

  - [ ] Adicionar atributos de estado: Direction direction (direção atual) e Direction nextDirection (próxima direção a
  aplicar, para evitar reversão no mesmo tick).
  - [ ] Adicionar constante int INITIAL_LENGTH (ex.: 3) e o ponto inicial da cabeça (ex.: no centro do tabuleiro).
  - [ ] Construtor Snake(Point start, int initialLength, Direction initialDirection) que popula corpoCobra com os
  segmentos iniciais na direção oposta à cabeça (ex.: para RIGHT, segmentos ficam à esquerda).
  - [ ] Inicializar direction e nextDirection no construtor.

  2. Movimento

  - [ ] Implementar setDirection(Direction newDirection) validando com Direction.isOpposite(...) para impedir reversão
  180° (a cobra não pode virar sobre o próprio corpo).
  - [ ] Implementar move() que: aplica nextDirection → direction, calcula nova Point da cabeça, adiciona ao início de
  corpoCobra, e remove o último segmento (crescimento normal).
  - [ ] Implementar sobrecarga move(boolean grow) ou método grow() separado, para quando a cobra comer: não remover a
  cauda.
  - [ ] Garantir que nextDirection seja consumida uma vez por tick (para não acumular inputs).

  3. Crescimento

  - [ ] Método grow() que apenas marca que o próximo move não deve remover a cauda (flag interna pendingGrowth ou
  contador de crescimento).
  - [ ] Suportar múltiplos crescimentos consecutivos (usar contador, não boolean).

  4. Colisões

  - [ ] boolean collidesWithWall(int width, int height) — verifica se a cabeça saiu dos limites (x < 0, x >= width, y <
  0, y >= height).
  - [ ] boolean collidesWithSelf() — verifica se a cabeça (primeiro elemento) está em uma posição já ocupada pelo resto
  do corpo.
  - [ ] boolean isDead(int width, int height) — agrega as duas verificações acima (conveniência para o GameState).

  5. Acesso ao corpo (para renderização e regras)

  - [ ] Point getHead() — retorna corpoCobra.getFirst().
  - [ ] List<Point> getBody() — retorna corpoCobra (ou uma cópia imutável via List.copyOf para encapsulamento).
  - [ ] int getLength() — corpoCobra.size().
  - [ ] boolean occupies(Point p) — útil para Food não spawnar em cima da cobra. 

  6. Reset / reinício

  - [ ] Método reset(Point start, int initialLength, Direction initialDirection) para reiniciar a cobra após Game Over,
  sem precisar recriar a instância.
  - [ ] Limpar pendingGrowth no reset. 

  7. Robustez e qualidade

  - [ ] Trocar LinkedList<Point> corpoCobra por Deque<Point> na API pública (manter LinkedList internamente) para expor
  addFirst/removeLast em vez de vazar a implementação.
  - [ ] Tratar IllegalArgumentException no setDirection se newDirection for null.
  - [ ] Tornar corpoCobra private (atualmente é public, ferindo encapsulamento).
  - [ ] Adicionar equals/hashCode em Point (o record gera automaticamente — confirmar que está sendo usado) para as
  checagens de colisão ficarem limpas.
  - [ ] Considerar @Override toString() em Snake para debug/log.

  8. Testes

  - [ ] Criar SnakeTest (JUnit 5) cobrindo:
    - movimento básico e crescimento;
    - bloqueio de reversão 180°;
    - detecção de colisão com parede e consigo mesma; 
    - reset.