package com.ludensdomain.advice;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
/*
 * setter 메서드를 사용하면 객체가 immutable 하지 않게 되며 의도치 않게 값이 바뀔 수 있는 환경에 노출돼 에러를 일으킬 수 있습니다.
 * 따라서 의도했던 결과를 가지기 위해 @Builder를 이용합니다.
 *
 * 다만, @Builder를 사용할 때 주의할 점은 클래스에 선언할 경우 객체를 생성할 때 모든 필드값을 받을 수 있게 해줍니다.
 * 왜냐하면 클래스에 선언하면 자동으로 생성자에 모든 멤버 필드의 매개변수를 받기 때문입니다.
 * 변형되면 안 되거나, 받을 의도를 가지지 않는 필드값이 있을 경우에는 생성자에 @Builder를 선언해주는 게 좋습니다.
 *
 * 또한 메시지를 받지 못하는 경우 NullPointerException을 방지하기 위해 @NonNull을 추가해줬습니다.
 */
public class ExceptionResponse {

    @NonNull private final String message;

}
