-- Categorias
INSERT INTO categorias (nome) VALUES ('Reparos e Manutenção');
INSERT INTO categorias (nome) VALUES ('Beleza e Estética');
INSERT INTO categorias (nome) VALUES ('Limpeza e Cuidados Domésticos');
INSERT INTO categorias (nome) VALUES ('Cuidados Pessoais');
INSERT INTO categorias (nome) VALUES ('Alimentação');
INSERT INTO categorias (nome) VALUES ('Serviços Gerais');
INSERT INTO categorias (nome) VALUES ('Educação e Aulas');
INSERT INTO categorias (nome) VALUES ('Eventos e Festas');
INSERT INTO categorias (nome) VALUES ('Artesanato e Costura');
INSERT INTO categorias (nome) VALUES ('Tecnologia Popular');
INSERT INTO categorias (nome) VALUES ('Transporte Alternativo');

-- Reparos e Manutenção
INSERT INTO servicos (nome, categoria_id) VALUES ('Conserto de eletrodomésticos', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Serviço de pedreiro', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Serviço de encanador', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Serviço de eletricista', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Pintura residencial', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));

-- Beleza e Estética
INSERT INTO servicos (nome, categoria_id) VALUES ('Corte de cabelo masculino', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Corte de cabelo feminino', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Manicure e pedicure', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Design de sobrancelhas', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Maquiagem para eventos', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));

-- Limpeza e Cuidados Domésticos
INSERT INTO servicos (nome, categoria_id) VALUES ('Faxina residencial', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Limpeza pós-obra', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Passar roupas', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Cozinheira diarista', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Lavagem de sofá', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));

-- Cuidados Pessoais
INSERT INTO servicos (nome, categoria_id) VALUES ('Babá por hora', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Cuidador de idosos', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Acompanhante hospitalar', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Aplicação de medicamentos', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Massagista', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));

-- Alimentação
INSERT INTO servicos (nome, categoria_id) VALUES ('Venda de marmita', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Bolos para festas', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Doces artesanais', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Lanches naturais', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Feijoada de domingo', (SELECT id FROM categorias WHERE nome = 'Alimentação'));

-- Serviços Gerais
INSERT INTO servicos (nome, categoria_id) VALUES ('Digitação de documentos', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Montagem de currículo', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Organização de armário', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Compra de supermercado', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Acompanhamento em consultas', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));

-- Educação e Aulas
INSERT INTO servicos (nome, categoria_id) VALUES ('Aula particular de matemática', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Aula de reforço escolar', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Aula de português', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Inglês básico', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Informática para iniciantes', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));

-- Eventos e Festas
INSERT INTO servicos (nome, categoria_id) VALUES ('Aluguel de caixa de som', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Serviço de garçom para festa', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Montagem de mesa de aniversário', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Decoração com balão', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Animador de festa infantil', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));

-- Artesanato e Costura
INSERT INTO servicos (nome, categoria_id) VALUES ('Fazer barra de calça', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Confecção de pano de prato', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Bordado manual', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Crochê sob encomenda', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));

-- Tecnologia Popular
INSERT INTO servicos (nome, categoria_id) VALUES ('Formatação de celular', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Instalação de app de banco', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Troca de display de celular', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Configurar Wi-Fi doméstico', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Atualização de software', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));

-- Transporte Alternativo
INSERT INTO servicos (nome, categoria_id) VALUES ('Mototáxi', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Frete com kombi', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Leva compra da feira', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Entrega com bicicleta', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, categoria_id) VALUES ('Corrida até o posto', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
