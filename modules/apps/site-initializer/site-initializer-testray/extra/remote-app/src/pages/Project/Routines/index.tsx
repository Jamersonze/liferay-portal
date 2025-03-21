/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {useParams} from 'react-router-dom';

import Container from '../../../components/Layout/Container';
import ListViewRest from '../../../components/ListView';
import ProgressBar from '../../../components/ProgressBar';
import i18n from '../../../i18n';
import {filters} from '../../../schema/filter';
import {getTimeFromNow} from '../../../util/date';
import {searchUtil} from '../../../util/search';
import RoutineModal from './RoutineModal';
import useRoutineActions from './useRoutineActions';

const Routines = () => {
	const {projectId: _projectId} = useParams();
	const {actions, formModal} = useRoutineActions();

	const projectId = Number(_projectId);

	return (
		<Container>
			<ListViewRest
				forceRefetch={formModal.forceRefetch}
				managementToolbarProps={{
					addButton: () => formModal.modal.open(),
					filterFields: filters.routines,
					title: i18n.translate('routines'),
				}}
				resource="/routines"
				tableProps={{
					actions,
					columns: [
						{
							clickable: true,
							key: 'name',
							sorteable: true,
							value: i18n.translate('routine'),
						},
						{
							clickable: true,
							key: 'dateCreated',
							render: getTimeFromNow,
							value: i18n.translate('execution-date'),
						},
						{
							clickable: true,
							key: 'failed',
							render: () => 0,
							value: i18n.translate('failed'),
						},
						{
							clickable: true,
							key: 'blocked',
							render: () => 0,
							value: i18n.translate('blocked'),
						},
						{
							clickable: true,
							key: 'test_fix',
							render: () => 0,
							value: i18n.translate('test-fix'),
						},
						{
							clickable: true,
							key: 'metrics',
							render: () => (
								<ProgressBar
									items={{
										blocked: 0,
										failed: 1,
										passed: 70,
									}}
								/>
							),
							size: 'sm',
							value: i18n.translate('metrics'),
						},
					],
					navigateTo: ({id}) => id?.toString(),
				}}
				variables={{
					filter: searchUtil.eq('projectId', projectId),
				}}
			/>

			<RoutineModal modal={formModal.modal} projectId={projectId} />
		</Container>
	);
};

export default Routines;
