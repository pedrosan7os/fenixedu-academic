# Disciplinas execu��o n�o emparelhadas com curriculares... FENIX...
select de.*
from fenix.disciplina_execucao de LEFT JOIN fenix.disciplina_curricular_disciplina_execucao ec on de.codigoInterno = ec.chaveDisciplinaExecucao
where ec.chaveDisciplinaExecucao IS NULL order by de.nome;

# Lista de Salas a tratar (poder� haver mais mas estas j� v�o dar trabalho)
select *
from fenix.sala
where tipo = " ";


