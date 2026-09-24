import { useCallback, useEffect, useRef, useState } from "react";
import { Building2 } from "lucide-react";
import { api } from "./api";
import type { EstadoSimulacao } from "./types";
import { Grid } from "./components/Grid";
import { Controls } from "./components/Controls";
import { StatsPanel } from "./components/StatsPanel";
import "./App.css";

const INTERVALO_MS = 500;

export default function App() {
  const [estado, setEstado] = useState<EstadoSimulacao | null>(null);
  const [erro, setErro] = useState<string | null>(null);
  const [rodando, setRodando] = useState(false);
  const intervaloRef = useRef<number | null>(null);

  useEffect(() => {
    api
      .obterAtual()
      .then(setEstado)
      .catch(() => setErro("Não foi possível conectar ao backend em http://localhost:8080."));
  }, []);

  useEffect(() => {
    if (!rodando) {
      if (intervaloRef.current) {
        window.clearInterval(intervaloRef.current);
        intervaloRef.current = null;
      }
      return;
    }

    intervaloRef.current = window.setInterval(async () => {
      try {
        const novoEstado = await api.executarCiclo();
        setEstado(novoEstado);
      } catch {
        setErro("Conexão com o backend perdida.");
        setRodando(false);
      }
    }, INTERVALO_MS);

    return () => {
      if (intervaloRef.current) {
        window.clearInterval(intervaloRef.current);
      }
    };
  }, [rodando]);

  const avancar = useCallback(async () => {
    try {
      const novoEstado = await api.executarCiclo();
      setEstado(novoEstado);
    } catch {
      setErro("Não foi possível avançar o ciclo.");
    }
  }, []);

  const reiniciar = useCallback(async (largura: number, altura: number, qualidadeDeVida: number) => {
    setRodando(false);
    try {
      const novoEstado = await api.nova(largura, altura, qualidadeDeVida);
      setEstado(novoEstado);
      setErro(null);
    } catch {
      setErro("Não foi possível criar a nova simulação.");
    }
  }, []);

  const alterarQualidadeDeVida = useCallback(async (qualidadeDeVida: number) => {
    try {
      const novoEstado = await api.alterarQualidadeDeVida(qualidadeDeVida);
      setEstado(novoEstado);
    } catch {
      setErro("Não foi possível alterar a qualidade de vida.");
    }
  }, []);

  return (
    <div className="app">
      <header className="app-header">
        <Building2 size={22} />
        <h1>UrbanSim</h1>
        <span className="app-subtitulo">Autômato celular baseado em populações</span>
      </header>

      {erro && <div className="app-erro">{erro}</div>}

      {!estado ? (
        <div className="app-carregando">Carregando simulação...</div>
      ) : (
        <div className="app-corpo">
          <Controls
            rodando={rodando}
            onToggleRodando={() => setRodando((r) => !r)}
            onAvancar={avancar}
            onReiniciar={reiniciar}
            qualidadeDeVida={estado.populacao.qualidadeDeVida}
            onAlterarQualidadeDeVida={alterarQualidadeDeVida}
          />

          <div className="app-conteudo">
            <StatsPanel estado={estado} />
            <Grid grade={estado.grade} />
          </div>
        </div>
      )}
    </div>
  );
}
