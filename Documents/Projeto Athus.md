# Projeto Athus

## API REST - Java
### Mobile
- Funcionalidades voltadas à praticidade dos usuários comuns (prestador de serviços / contratante)
- Funcionalidades reduzidas de usuários ADM e TÉCNICOS

### Site
- Funcionalidades similares ao app para usuários comuns
- Funcionalidades extras para ADM e TÉCNICOS
  - Gerenciar outros usuários
  - Banir / Bloquear anúncios maliciosos ou fraudulentos

## Funcionalidades
### Anúncios e Contratação
- Anunciar / contratar serviços  

### Chat
- Chat entre prestador e contratante
- Chat para usuários
- Segurança das informações com criptografia visando a LGPD (Lei Geral de Proteção de Dados)

### Redirecionamento para Contato Externo
- Contato via WhatsApp

### Segurança dos Dados
- Criptografia de cookies de sessão utilizando a biblioteca do JWT
- Desativar sessão após determinado período (exemplo: 30 minutos de inatividade)
- Login com autenticação das principais bigtechs (Google, Microsoft)
- Autenticação de e-mail / telefone
- Criptografia dos dados utilizando **AES-256**
- Criptografia das mensagens do chat
- Autenticação de dois fatores (2FA) para segurança adicional
- Proteção contra ataques de força bruta e injeção de SQL

### Portal ADM pelo Website
- Criar no site a parte que o ADM gerencia seus privilégios, como criação de novos usuários técnicos, etc.
- Painel de denúncias para que usuários possam reportar anúncios suspeitos
- Auditoria completa das atividades realizadas pelos administradores
- Controle granular de permissões, permitindo diferentes níveis de acesso para ADM e técnicos

### Usuários TÉCNICOS
- Validar as diretrizes de cada anúncio
- Liberar anúncio somente se seguir com as políticas do app
- Garantir segurança e transparência no ambiente desenvolvido
- Bloquear/banir anúncios maliciosos ou suspeitos de fraudes
- Processo de revisão detalhado para cada anúncio (rascunho → revisão técnica → aprovação/rejeição)
- Implementação de **detecção automática de fraudes** por palavras-chave suspeitas

### Servidor SMTP para envio de e-mails
- Informar usuários sobre atualizações de contratos
- Alertar sobre novas mensagens

### Mecanismos de Avaliação
- Moderação de avaliações para evitar abusos
- Feedback direto para prestadores de serviço para melhoria contínua

### Mecanismo de Geolocalização
- Filtro por localização para encontrar serviços próximos
- Mapa interativo com prestadores disponíveis na região
- Estimativa de tempo de deslocamento baseado no Google Maps

### Filtros Avançados para Busca de Prestadores
- Filtro por nível de experiência e avaliações
- Opção de ver apenas prestadores verificados
- Busca com palavras-chave e categorias detalhadas
- Opção de favoritar prestadores para facilitar buscas futuras
- Sistema de recomendação baseado no histórico de buscas e contratações anteriores

## Extras
### Monitoramento de Logs
- Monitoramento de acessos e tentativas de login suspeitas
- Registro de logs de atividades críticas (exclusão de anúncios, alterações de usuários, etc.)
- Integração com ferramentas de observabilidade (ex: **Prometheus, Grafana, Elastic Stack**)
- Alertas automáticos para comportamentos suspeitos (exemplo: acessos simultâneos de diferentes regiões)

### Expansão e Escalabilidade
- Banco de Dados escalável (ex: PostgreSQL, MongoDB)
- Cache para otimizar buscas e reduzir carga no banco de dados (Redis)

### Suporte ao Cliente
- Canal de suporte via chat ou ticketing system
- FAQ integrado para dúvidas frequentes
- Central de denúncias para usuários reportarem anúncios suspeitos

### Gamificação e Engajamento
- Sistema de badges/conquistas para prestadores de serviço bem avaliados
- Ranking dinâmico de melhores prestadores por categoria (baseado em avaliações e volume de contratos)
- Recompensas por participação ativa, como selos de "Prestador Verificado"
- Missões e desafios (ex: "Complete 10 serviços bem avaliados e ganhe um selo de destaque")

### Perfis Verificados e Segurança
- Verificação de identidade via documento (RG, CNH, Passaporte)
- Selo de "Prestador Certificado" para usuários que enviam documentação válida
- Consulta de antecedentes ou validação de empresas via CNPJ
- Sistema de análise de comportamento para detectar possíveis fraudes ou perfis falsos

### Relatórios e Estatísticas para Usuários
- Dashboard com número de contratos fechados e ganhos
- Gráficos de desempenho para prestadores (ex: média de avaliações, serviços mais contratados)
- Tendências de mercado (ex: categorias de serviço mais buscadas na plataforma)
- Gráficos interativos mostrando crescimento do prestador na plataforma
- Previsão de ganhos com base nos serviços anteriores

### Modo Offline para App
- Acesso ao histórico de mensagens recente
- Criação de rascunhos de anúncios para publicação posterior

### Agenda Integrada para Prestadores
- Calendário integrado para agendar serviços
- Sincronização com Google Calendar ou Outlook
- Lembretes automáticos de serviços agendados via push ou e-mail
