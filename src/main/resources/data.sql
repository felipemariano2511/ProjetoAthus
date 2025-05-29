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
INSERT INTO servicos (nome, id_categoria) VALUES ('Conserto de eletrodomésticos', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Serviço de pedreiro', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Serviço de encanador', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Serviço de eletricista', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Pintura residencial', (SELECT id FROM categorias WHERE nome = 'Reparos e Manutenção'));

-- Beleza e Estética
INSERT INTO servicos (nome, id_categoria) VALUES ('Corte de cabelo masculino', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Corte de cabelo feminino', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Manicure e pedicure', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Design de sobrancelhas', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Maquiagem para eventos', (SELECT id FROM categorias WHERE nome = 'Beleza e Estética'));

-- Limpeza e Cuidados Domésticos
INSERT INTO servicos (nome, id_categoria) VALUES ('Faxina residencial', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Limpeza pós-obra', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Passar roupas', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Cozinheira diarista', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Lavagem de sofá', (SELECT id FROM categorias WHERE nome = 'Limpeza e Cuidados Domésticos'));

-- Cuidados Pessoais
INSERT INTO servicos (nome, id_categoria) VALUES ('Babá por hora', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Cuidador de idosos', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Acompanhante hospitalar', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Aplicação de medicamentos', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Massagista', (SELECT id FROM categorias WHERE nome = 'Cuidados Pessoais'));

-- Alimentação
INSERT INTO servicos (nome, id_categoria) VALUES ('Venda de marmita', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Bolos para festas', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Doces artesanais', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Lanches naturais', (SELECT id FROM categorias WHERE nome = 'Alimentação'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Feijoada de domingo', (SELECT id FROM categorias WHERE nome = 'Alimentação'));

-- Serviços Gerais
INSERT INTO servicos (nome, id_categoria) VALUES ('Digitação de documentos', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Montagem de currículo', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Organização de armário', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Compra de supermercado', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Acompanhamento em consultas', (SELECT id FROM categorias WHERE nome = 'Serviços Gerais'));

-- Educação e Aulas
INSERT INTO servicos (nome, id_categoria) VALUES ('Aula particular de matemática', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Aula de reforço escolar', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Aula de português', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Inglês básico', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Informática para iniciantes', (SELECT id FROM categorias WHERE nome = 'Educação e Aulas'));

-- Eventos e Festas
INSERT INTO servicos (nome, id_categoria) VALUES ('Aluguel de caixa de som', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Serviço de garçom para festa', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Montagem de mesa de aniversário', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Decoração com balão', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Animador de festa infantil', (SELECT id FROM categorias WHERE nome = 'Eventos e Festas'));

-- Artesanato e Costura
INSERT INTO servicos (nome, id_categoria) VALUES ('Fazer barra de calça', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Confecção de pano de prato', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Bordado manual', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Crochê sob encomenda', (SELECT id FROM categorias WHERE nome = 'Artesanato e Costura'));

-- Tecnologia Popular
INSERT INTO servicos (nome, id_categoria) VALUES ('Formatação de celular', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Instalação de app de banco', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Troca de display de celular', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Configurar Wi-Fi doméstico', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Atualização de software', (SELECT id FROM categorias WHERE nome = 'Tecnologia Popular'));

-- Transporte Alternativo
INSERT INTO servicos (nome, id_categoria) VALUES ('Mototáxi', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Frete com kombi', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Leva compra da feira', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Entrega com bicicleta', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
INSERT INTO servicos (nome, id_categoria) VALUES ('Corrida até o posto', (SELECT id FROM categorias WHERE nome = 'Transporte Alternativo'));
