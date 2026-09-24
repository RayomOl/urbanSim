import type { Grade, EstadoCelula } from "../../types";
import "./styles.css";

const CORES: Record<EstadoCelula, string> = {
  ABANDONADA: "var(--cor-abandonada)",
  BAIXA_QUALIDADE: "var(--cor-baixa)",
  MEDIA_QUALIDADE: "var(--cor-media)",
  ALTA_QUALIDADE: "var(--cor-alta)",
};

const ROTULOS: Record<EstadoCelula, string> = {
  ABANDONADA: "Abandonada",
  BAIXA_QUALIDADE: "Baixa qualidade",
  MEDIA_QUALIDADE: "Média qualidade",
  ALTA_QUALIDADE: "Alta qualidade",
};

interface GridProps {
  grade: Grade;
}

export function Grid({ grade }: GridProps) {
  return (
    <div className="grid-wrapper">
      <div
        className="grid"
        style={{
          gridTemplateColumns: `repeat(${grade.largura}, 1fr)`,
          gridTemplateRows: `repeat(${grade.altura}, 1fr)`,
        }}
      >
        {grade.celulas.map((celula) => (
          <div
            key={`${celula.x}-${celula.y}`}
            className="celula"
            style={{
              background: celula.estado ? CORES[celula.estado] : "var(--cor-vazia)",
            }}
            title={celula.estado ? ROTULOS[celula.estado] : "Vazia"}
          />
        ))}
      </div>

      <div className="legenda">
        <LegendaItem cor="var(--cor-vazia)" rotulo="Vazia" tracejado />
        <LegendaItem cor="var(--cor-abandonada)" rotulo="Abandonada" />
        <LegendaItem cor="var(--cor-baixa)" rotulo="Baixa qualidade" />
        <LegendaItem cor="var(--cor-media)" rotulo="Média qualidade" />
        <LegendaItem cor="var(--cor-alta)" rotulo="Alta qualidade" />
      </div>
    </div>
  );
}

function LegendaItem({ cor, rotulo, tracejado }: { cor: string; rotulo: string; tracejado?: boolean }) {
  return (
    <div className="legenda-item">
      <span
        className="legenda-cor"
        style={{
          background: cor,
          border: tracejado ? "1px dashed var(--border)" : "1px solid transparent",
        }}
      />
      <span>{rotulo}</span>
    </div>
  );
}
