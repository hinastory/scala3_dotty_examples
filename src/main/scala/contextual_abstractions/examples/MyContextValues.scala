package MyContextValues

import MyContextDefs.*

given ctxA: ContextA = ContextA("sunny") // コンテキストの生成(`given`インスタンス)
given ctxB: ContextB = ContextB(2021) // コンテキストの生成(`given`インスタンス)