import type { ReactNode } from "react";
import { Clock, Smile, TrendingUp, Building2 } from "lucide-react";
import type { EstadoSimulacao } from "../../types";
import "./styles.css";

interface StatsPanelProps {
  estado: EstadoSimulacao;
}

export function StatsPanel({ estado }: StatsPanelProps) {
  const ocupadas = estado.grade.celulas.filter((c) => c.estado !== null).length;
  const total = estado.grade.celulas.length;

  return (
    <div className="stats-panel">
      <StatItem icone={<Clock size={18} />} rotulo="Ciclo" valor={estado.ciclo.toString()} />
      <StatItem
        icone={<Smile size={18} />}
        rotulo="Felicidade"
        valor={`${estado.populacao.felicidade.toFixed(1)} / 100`}
      />
      <StatItem
        icone={<TrendingUp size={18} />}
        rotulo="Taxa de crescimento"
        valor={`${(estado.populacao.taxaDeCrescimento * 100).toFixed(1)}%`}
      />
      <StatItem
        icone={<Building2 size={18} />}
        rotulo="Construções"
        valor={`${ocupadas} / ${total}`}
      />
    </div>
  );
}

function StatItem({ icone, rotulo, valor }: { icone: ReactNode; rotulo: string; valor: string }) {
  return (
    <div className="stat-item">
      <div className="stat-icone">{icone}</div>
      <div className="stat-texto">
        <span className="stat-rotulo">{rotulo}</span>
        <span className="stat-valor">{valor}</span>
      </div>
    </div>
  );
}
