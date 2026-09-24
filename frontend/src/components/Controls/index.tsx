import { useState } from "react";
import { Play, Pause, SkipForward, RotateCcw } from "lucide-react";
import "./styles.css";

interface ControlsProps {
  rodando: boolean;
  onToggleRodando: () => void;
  onAvancar: () => void;
  onReiniciar: (largura: number, altura: number, qualidadeDeVida: number) => void;
  qualidadeDeVida: number;
  onAlterarQualidadeDeVida: (qv: number) => void;
}

const OPCOES_QV = [
  { valor: 0, rotulo: "Baixa" },
  { valor: 1, rotulo: "Média" },
  { valor: 2, rotulo: "Alta" },
];

export function Controls({
  rodando,
  onToggleRodando,
  onAvancar,
  onReiniciar,
  qualidadeDeVida,
  onAlterarQualidadeDeVida,
}: ControlsProps) {
  const [largura, setLargura] = useState(20);
  const [altura, setAltura] = useState(20);

  return (
    <div className="controls">
      <div className="controls-secao">
        <span className="controls-titulo">Simulação</span>
        <div className="controls-botoes">
          <button className="botao botao-primario" onClick={onToggleRodando}>
            {rodando ? <Pause size={16} /> : <Play size={16} />}
            {rodando ? "Pausar" : "Rodar"}
          </button>
          <button className="botao" onClick={onAvancar} disabled={rodando}>
            <SkipForward size={16} />
            Avançar ciclo
          </button>
        </div>
      </div>

      <div className="controls-secao">
        <span className="controls-titulo">Qualidade de vida</span>
        <div className="controls-segmentado">
          {OPCOES_QV.map((opcao) => (
            <button
              key={opcao.valor}
              className={`segmento ${qualidadeDeVida === opcao.valor ? "segmento-ativo" : ""}`}
              onClick={() => onAlterarQualidadeDeVida(opcao.valor)}
            >
              {opcao.rotulo}
            </button>
          ))}
        </div>
      </div>

      <div className="controls-secao">
        <span className="controls-titulo">Nova simulação</span>
        <div className="controls-grade-form">
          <label>
            Largura
            <input
              type="number"
              min={5}
              max={60}
              value={largura}
              onChange={(e) => setLargura(Number(e.target.value))}
            />
          </label>
          <label>
            Altura
            <input
              type="number"
              min={5}
              max={60}
              value={altura}
              onChange={(e) => setAltura(Number(e.target.value))}
            />
          </label>
        </div>
        <button
          className="botao"
          onClick={() => onReiniciar(largura, altura, qualidadeDeVida)}
        >
          <RotateCcw size={16} />
          Reiniciar
        </button>
      </div>
    </div>
  );
}
