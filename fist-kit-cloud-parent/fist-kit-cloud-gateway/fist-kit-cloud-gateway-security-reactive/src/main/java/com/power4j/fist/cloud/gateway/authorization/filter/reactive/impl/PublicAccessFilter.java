/*
 *  Copyright 2021 ChenJun (power4j@outlook.com & https://github.com/John-Chan)
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.gnu.org/licenses/lgpl.html
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.power4j.fist.cloud.gateway.authorization.filter.reactive.impl;

import com.power4j.fist.cloud.gateway.authorization.domain.AuthContext;
import com.power4j.fist.cloud.gateway.authorization.domain.AuthProblem;
import com.power4j.fist.cloud.gateway.authorization.filter.reactive.GatewayAuthFilter;
import com.power4j.fist.security.core.authorization.domain.PermissionDefinition;
import com.power4j.fist.security.core.authorization.filter.reactive.ServerAuthFilterChain;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2021/11/26
 * @since 1.0
 */
@Slf4j
public class PublicAccessFilter implements GatewayAuthFilter {

	@Override
	public Mono<Void> filter(AuthContext ctx, ServerAuthFilterChain<AuthContext> chain) {
		final PermissionDefinition permissionDefinition = ctx.getPermissionDefinition();

		if (permissionDefinition != null && permissionDefinition.isPublicAccess()) {
			if (log.isTraceEnabled()) {
				log.trace("public access rule matches. => {}", ctx.getInbound().shortDescription());
			}
			return exitChain(ctx, AuthProblem.PUB_ACCESS);
		}
		return doNext(ctx, chain);
	}

}
