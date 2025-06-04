-- Inserção de usuários com imagem, nível, ativo, banido e prestador_servico
INSERT INTO usuarios (nome_completo, nome, email, senha, nivel, ativo, banido, prestador_servico, imagem_perfil)
VALUES
    ('Administrador Root', 'root', 'athusprojeto@gmail.com', 'root123', 3, 1, 0, 1, 'storage/imagens/usuarios/usuario.png'),
    ('Felipe Mariano', 'felipe', 'felipe.mariano2511@gmail.com', 'senha123', 0, 1, 0, 1, 'storage/imagens/usuarios/felipemariano.jpg');

-- Prestações de serviço para o usuário root
INSERT INTO prestacao_servico (
    id_usuario, id_servico, descricao_completa, valor, data_criacao, descricao_curta, imagens, ativo
) VALUES
      (1, (SELECT id FROM servicos WHERE nome = 'Serviço de pedreiro' LIMIT 1),
    'Faço serviços completos de alvenaria e reforma. Experiência de mais de 10 anos.',
    350.0, NOW(), 'Serviço de pedreiro profissional',
    '["storage/imagens/anuncios/pedreiro1.jpg", "storage/imagens/anuncios/pedreiro2.jpg"]',
    true),

(1, (SELECT id FROM servicos WHERE nome = 'Limpeza pós-obra' LIMIT 1),
 'Limpeza pesada especializada em pós-obra. Equipamentos próprios.',
 280.0, NOW(), 'Limpeza pós-obra completa',
 '["storage/imagens/anuncios/limpeza1.jpg", "storage/imagens/anuncios/limpeza2.jpg"]',
 true);

-- Prestações de serviço para Felipe Mariano
INSERT INTO prestacao_servico (
    id_usuario, id_servico, descricao_completa, valor, data_criacao, descricao_curta, imagens, ativo
) VALUES
      (2, (SELECT id FROM servicos WHERE nome = 'Corte de cabelo masculino' LIMIT 1),
    'Barbeiro profissional com atendimento em domicílio. Higiene e estilo garantidos.',
    40.0, NOW(), 'Corte masculino delivery',
    '["storage/imagens/anuncios/corte1.jpg", "storage/imagens/anuncios/corte2.jpg"]',
    true),

(2, (SELECT id FROM servicos WHERE nome = 'Aula de português' LIMIT 1),
 'Reforço escolar de português com foco em gramática e redação. Atendo alunos de todas as idades.',
 60.0, NOW(), 'Aula de português particular',
 '["storage/imagens/anuncios/portugues1.jpg"]',
 true);

INSERT INTO prestacao_servico (
    id_usuario, id_servico, descricao_completa, valor, data_criacao, descricao_curta, imagens, ativo
) VALUES
      (1, (SELECT id FROM servicos WHERE nome = 'Instalação de pisos' LIMIT 1),
    'Especialista em colocação de porcelanato, cerâmica e vinílico. Agilidade e perfeição nos acabamentos.',
    480.0, NOW(), 'Instalação de pisos de todos os tipos',
    '["storage/imagens/anuncios/pisos1.jpg", "storage/imagens/anuncios/pisos2.jpg"]',
    true),

(1, (SELECT id FROM servicos WHERE nome = 'Pintura residencial' LIMIT 1),
 'Pintura interna e externa com materiais de qualidade. Cores vibrantes e acabamento fino.',
 390.0, NOW(), 'Pintura de casas e apartamentos',
 '["storage/imagens/anuncios/pintura1.jpg", "storage/imagens/anuncios/pintura2.jpg"]',
 true),

(1, (SELECT id FROM servicos WHERE nome = 'Encanador' LIMIT 1),
 'Serviços de encanamento para vazamentos, instalações e manutenção. Atendimento emergencial disponível.',
 250.0, NOW(), 'Serviços hidráulicos em geral',
 '["storage/imagens/anuncios/encanador1.jpg", "storage/imagens/anuncios/encanador2.jpg"]',
 true),

(1, (SELECT id FROM servicos WHERE nome = 'Elétrica residencial' LIMIT 1),
 'Instalações e reparos elétricos com segurança e certificado NR10.',
 300.0, NOW(), 'Serviços elétricos com garantia',
 '["storage/imagens/anuncios/eletrica1.jpg", "storage/imagens/anuncios/eletrica2.jpg"]',
 true),

(1, (SELECT id FROM servicos WHERE nome = 'Montagem de móveis' LIMIT 1),
 'Montador experiente para móveis de qualquer fabricante. Rápido e sem sujeira.',
 150.0, NOW(), 'Montagem de móveis com eficiência',
 '["storage/imagens/anuncios/moveis1.jpg"]',
 true);

-- Novas prestações de serviço para Felipe Mariano
INSERT INTO prestacao_servico (
    id_usuario, id_servico, descricao_completa, valor, data_criacao, descricao_curta, imagens, ativo
) VALUES
      (2, (SELECT id FROM servicos WHERE nome = 'Design gráfico' LIMIT 1),
    'Criação de logotipos, banners, cartões de visita e redes sociais. Pacotes promocionais.',
    120.0, NOW(), 'Serviços de design gráfico criativo',
    '["storage/imagens/anuncios/design1.jpg", "storage/imagens/anuncios/design2.jpg"]',
    true),

(2, (SELECT id FROM servicos WHERE nome = 'Criação de sites' LIMIT 1),
 'Desenvolvimento de sites institucionais e landing pages com design responsivo.',
 750.0, NOW(), 'Sites profissionais para seu negócio',
 '["storage/imagens/anuncios/sites1.jpg"]',
 true),

(2, (SELECT id FROM servicos WHERE nome = 'Aula de matemática' LIMIT 1),
 'Reforço de matemática básica ao ensino médio. Atendo presencial e online.',
 55.0, NOW(), 'Aulas particulares de matemática',
 '["storage/imagens/anuncios/matematica1.jpg"]',
 true),

(2, (SELECT id FROM servicos WHERE nome = 'Fotografia de eventos' LIMIT 1),
 'Cobertura fotográfica para aniversários e casamentos. Entrego fotos tratadas em alta qualidade.',
 500.0, NOW(), 'Fotografia profissional de eventos',
 '["storage/imagens/anuncios/fotografia1.jpg", "storage/imagens/anuncios/fotografia2.jpg"]',
 true),

(2, (SELECT id FROM servicos WHERE nome = 'Tradução de textos' LIMIT 1),
 'Tradução de inglês para português com revisão gramatical e fidelidade ao conteúdo.',
 80.0, NOW(), 'Tradução de documentos e artigos',
 '["storage/imagens/anuncios/traducao1.jpg"]',
 true);