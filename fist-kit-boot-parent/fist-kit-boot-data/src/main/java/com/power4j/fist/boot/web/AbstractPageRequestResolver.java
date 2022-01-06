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

package com.power4j.fist.boot.web;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.Objects;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2021/12/15
 * @since 1.0
 */
public abstract class AbstractPageRequestResolver implements HandlerMethodArgumentResolver {

	@Getter
	private String pageNumberKey = "pageNumber";

	@Getter
	private String pageSizeKey = "pageSize";

	public void setPageNumberKey(String pageNumberKey) {
		this.pageNumberKey = pageNumberKey;
	}

	public void setPageSizeKey(String pageSizeKey) {
		this.pageSizeKey = pageSizeKey;
	}

	protected int parseInt(@Nullable String str, int defaultVal) {
		if (Objects.isNull(str) || str.isEmpty()) {
			return defaultVal;
		}
		try {
			return Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			return defaultVal;
		}
	}

}
