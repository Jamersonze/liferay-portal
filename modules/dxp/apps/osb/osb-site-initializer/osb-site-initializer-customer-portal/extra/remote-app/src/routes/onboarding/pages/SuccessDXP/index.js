import BaseButton from '../../../../common/components/BaseButton';
import {LiferayTheme} from '../../../../common/services/liferay';
import {API_BASE_URL} from '../../../../common/utils';
import Layout from '../../components/Layout';

const SuccessDXP = () => {
	function onClickDone() {
		window.location.href = `${API_BASE_URL}${LiferayTheme.getLiferaySiteName()}`;
	}

	return (
		<Layout
			footerProps={{
				middleButton: (
					<BaseButton displayType="primary" onClick={onClickDone}>
						Done
					</BaseButton>
				),
			}}
			headerProps={{
				helper:
					'We’ll need a few details to finish building your DXP environment(s).',
				title: 'Set up DXP Cloud',
			}}
		>
			<div className="container font-weight-bold pl-6 pr-6 pt-9 text-center">
				Thank you for submitting this request! Your DXP Cloud project
				will be provisioned in 2-3 business days. At that time, DXP
				Cloud Administrators will receive several onboarding emails,
				giving them access to all the DXP Cloud environments and tools
				included in your subscription.
			</div>
		</Layout>
	);
};

export default SuccessDXP;
